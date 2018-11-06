using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using TouristAttractions.Services.Model;

namespace TouristAttractions.Services.Contracts
{
    public interface IAddressService
    {
        Task<Address> GetAsync(Guid id);
    }
}
