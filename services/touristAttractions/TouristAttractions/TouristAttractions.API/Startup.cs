using AutoMapper;
using TouristAttractions.API.Common.Attributes;
using TouristAttractions.API.Common.Extensions;
using TouristAttractions.API.Common.Settings;
using TouristAttractions.IoC.Configuration.Profiles;
using TouristAttractions.Services;
using TouristAttractions.Services.Contracts;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Versioning;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Swashbuckle.AspNetCore.Swagger;
using Microsoft.Extensions.PlatformAbstractions;
using Microsoft.AspNetCore.Mvc.ApiExplorer;
using System.Reflection;
using System.IO;
using TouristAttractions.API.Controllers;
using Microsoft.AspNetCore.Mvc.Versioning.Conventions;
using TouristAttractions.API.Swagger;
using Microsoft.EntityFrameworkCore;
using TouristAttractions.Repositories;
using TouristAttractions.Services.Services;

namespace TouristAttractions.API
{
    public class Startup
    {
        public IConfiguration Configuration { get; private set; }
        public IHostingEnvironment HostingEnvironment { get; private set; }

        private AppSettings _appSettings;

        public Startup(IConfiguration configuration, IHostingEnvironment env)
        {
            HostingEnvironment = env;
            Configuration = configuration;
        }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {

            //API Explorer (for API Versioning)
            // add the versioned api explorer, which also adds IApiVersionDescriptionProvider service
            // note: the specified format code will format the version as "'v'major[.minor][-status]"
            services.AddMvcCore().AddVersionedApiExplorer(
                options =>
                {
                    options.GroupNameFormat = "'v'VVV";

                    // note: this option is only necessary when versioning by url segment. the SubstitutionFormat
                    // can also be used to control the format of the API version in route templates
                    options.SubstituteApiVersionInUrl = true;
                });


            services.AddMvc(
                opt => opt.Filters.Add(typeof(CustomFilterAttribute))
                )
                .SetCompatibilityVersion(Microsoft.AspNetCore.Mvc.CompatibilityVersion.Version_2_1);

            //API Version
            services.AddApiVersioning(
                o =>
                {
                    //o.Conventions.Controller<AttractionController>().HasApiVersion(1, 0);
                    o.ReportApiVersions = true;
                    o.AssumeDefaultVersionWhenUnspecified = true;
                    o.DefaultApiVersion = new ApiVersion(1, 0);
                    o.ApiVersionReader = new UrlSegmentApiVersionReader();
                }
                );

            //App settings
            var appSettingsSection = Configuration.GetSection("AppSettings");
            if (appSettingsSection == null)
                throw new System.Exception("No appsettings section has been found");

            services.Configure<AppSettings>(appSettingsSection);

            _appSettings = appSettingsSection.Get<AppSettings>();

            if (_appSettings.IsValid())
            {
                if (_appSettings.Swagger.Enabled)
                {
                    // Register the Swagger generator, defining 1 or more Swagger documents
                    services.AddSwaggerGen(options =>
                    {                        
                        // resolve the IApiVersionDescriptionProvider service
                        // note: that we have to build a temporary service provider here because one has not been created yet
                        var provider = services.BuildServiceProvider().GetRequiredService<IApiVersionDescriptionProvider>();

                        // add a swagger document for each discovered API version
                        // note: you might choose to skip or document deprecated API versions differently
                        foreach (var description in provider.ApiVersionDescriptions)
                        {
                            options.SwaggerDoc(description.GroupName, CreateInfoForApiVersion(description));
                        }

                        // add a custom operation filter which sets default values
                        options.OperationFilter<SwaggerDefaultValues>();

                        // integrate xml comments
                        //options.IncludeXmlComments(XmlCommentsFilePath);

                    });
                }
            }

            //Automap settings
            services.AddAutoMapper();
            ConfigureMaps();

            //Custom services (.NET CORE 2.1)
            services.AddTransient<IAttractionService, AttractionService>();
            services.AddTransient<IAddressService, AddressService>();
            services.AddTransient<IAttractionRepository, AttractionRepository>();
            services.AddTransient<IAddressRepository, AddressRepository>();
            services.AddDbContext<AttractionsDbContext> (options => options.UseSqlServer(
                Configuration.GetConnectionString("ConnectionString"),
                b => b.MigrationsAssembly("TouristAttractions.API")
            ));
        }


        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory, IApiVersionDescriptionProvider provider)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();

                loggerFactory.AddConsole(Configuration.GetSection("Logging"));
                loggerFactory.AddDebug();
            }
            else
            {
                app.UseExceptionHandler("/Error");
                app.UseHsts();
            }

            app.UseHttpsRedirection();

            //Swagger section
            if (_appSettings.IsValid())
            {
                if (_appSettings.Swagger.Enabled)
                {                    
                    app.UseSwagger();
                    
                    app.UseSwaggerUI(options =>
                    {
                        // build a swagger endpoint for each discovered API version
                        foreach (var description in provider.ApiVersionDescriptions)
                        {
                            options.SwaggerEndpoint($"/swagger/{description.GroupName}/swagger.json", description.GroupName.ToUpperInvariant());
                        }
                    });
                }
            }

            app.UseMvc();
        }

        private void ConfigureMaps()
        {
            //Mapping settings
            Mapper.Initialize(cfg =>
            {
                cfg.AddProfile<APIMappingProfile>();
                cfg.AddProfile<ServicesMappingProfile>();
            }
                );
        }

        string XmlCommentsFilePath
        {
            get
            {
                var basePath = PlatformServices.Default.Application.ApplicationBasePath;
                var fileName = typeof(Startup).GetTypeInfo().Assembly.GetName().Name + ".xml";
                return Path.Combine(basePath, fileName);
            }
        }

        Info CreateInfoForApiVersion(ApiVersionDescription description)
        {
            var info = new Info()
            {
                Title = $"{_appSettings.API.Title} {description.ApiVersion}",
                Version = description.ApiVersion.ToString(),
                Description = _appSettings.API.Description
            };

            if (description.IsDeprecated)
            {
                info.Description += " This API version has been deprecated.";
            }

            return info;
        }
    }
}
