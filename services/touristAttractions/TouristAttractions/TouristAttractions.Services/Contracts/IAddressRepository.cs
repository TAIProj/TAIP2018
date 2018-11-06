using System;
using TouristAttractions.API.DataContracts;

namespace TouristAttractions.Services.Contracts
{
    public interface IAddressRepository
    {
        void add(Address address);
        Address getById(Guid Id);
    }
}
