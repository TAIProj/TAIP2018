using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using TouristAttractions.Services.Model;

namespace TouristAttractions.Services.Contracts
{
    public interface IAttractionService
    {
        Task<string> CreateAsync(Attraction attraction);

        Task<bool> UpdateAsync(Attraction attraction);

        Task<bool> DeleteAsync(Guid id);

        Task<Attraction> GetAsync(Guid id);
    }
}
