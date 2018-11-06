using AutoMapper;
using TouristAttractions.Services.Model;
using TouristAttractions.Services.Contracts;
using Microsoft.Extensions.Options;
using System;
using System.Threading.Tasks;
using TouristAttractions.API.Common.Settings;
using DC = TouristAttractions.API.DataContracts;
using S = TouristAttractions.Services.Model;

namespace TouristAttractions.Services
{
    public class AttractionService : IAttractionService
    {
        private readonly IMapper _mapper;
        private readonly IAttractionRepository _repo;

        public AttractionService(IMapper mapper, IAttractionRepository repo)
        {
            _mapper = mapper;
            _repo = repo;
        }

        public Task<string> CreateAsync(Attraction attraction)
        {
            _repo.add(Mapper.Map<DC.Attraction>(attraction));
            return null;
        }

        public Task<bool> UpdateAsync(Attraction attraction)
        {
            _repo.update(Mapper.Map<DC.Attraction>(attraction));
            return null;
        }

        public Task<bool> DeleteAsync(Guid id)
        {
            _repo.delete(Mapper.Map<DC.Attraction>(id));
            return null;
        }

        public Task<Attraction> GetAsync(Guid id)
        {
            return Task.FromResult(Mapper.Map<S.Attraction>(_repo.getById(id)));
        }
    }
}
