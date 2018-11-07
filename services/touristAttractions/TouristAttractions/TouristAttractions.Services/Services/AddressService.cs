using AutoMapper;
using System;
using System.Threading.Tasks;
using TouristAttractions.Services.Contracts;
using TouristAttractions.Services.Model;
using S = TouristAttractions.Services.Model;

namespace TouristAttractions.Services.Services
{
    public class AddressService : IAddressService
    {
        private readonly IMapper _mapper;
        private readonly IAddressRepository _repo;

        public AddressService(IMapper mapper, IAddressRepository repo)
        {
            _mapper = mapper;
            _repo = repo;
        }

        public Task<Address> GetAsync(Guid id)
        {
            return Task.FromResult(Mapper.Map<S.Address>(_repo.getById(id)));
        }
    }
}
