using AutoMapper;
using DC = TouristAttractions.API.DataContracts;
using S = TouristAttractions.Services.Model;

namespace TouristAttractions.IoC.Configuration.Profiles
{
    public class APIMappingProfile : Profile
    {
        public APIMappingProfile()
        {
            CreateMap<S.Attraction, DC.Attraction>();
            CreateMap<S.Address, DC.Address>();
        }
    }
}
