using AutoMapper;
using TouristAttractions.API.Common.Settings;
using TouristAttractions.Services.Contracts;
using TouristAttractions.Services.Model;
using Microsoft.Extensions.Options;
using System;
using System.Threading.Tasks;

namespace TouristAttractions.Services
{
    public class AttractionService : IAttractionService
    {
        private string _connectionString = string.Empty;
        private readonly IMapper _mapper;

        public AttractionService(IOptions<AppSettings> settings, IMapper mapper)
        {
            _connectionString = settings?.Value?.ConnectionString;
            _mapper = mapper;
        }

        public Task<string> CreateAsync(Attraction attraction)
        {
            throw new NotImplementedException();
        }

        public Task<bool> UpdateAsync(Attraction attraction)
        {
            throw new NotImplementedException();
        }

        public Task<bool> DeleteAsync(string id)
        {
            throw new NotImplementedException();
        }

        public Task<Attraction> GetAsync(string id)
        {
            throw new NotImplementedException();
        }
    }
}
