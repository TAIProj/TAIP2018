using TouristAttractions.Services.Model;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace TouristAttractions.Services.Contracts
{
    public interface IAttractionService
    {
        Task<string> CreateAsync(Attraction attraction);

        Task<bool> UpdateAsync(Attraction attraction);

        Task<bool> DeleteAsync(string id);

        Task<Attraction> GetAsync(string id);
    }
}
