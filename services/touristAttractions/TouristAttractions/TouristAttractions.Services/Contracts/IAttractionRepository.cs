using System;
using System.Collections.Generic;
using TouristAttractions.API.DataContracts;

namespace TouristAttractions.Services.Contracts
{
    public interface IAttractionRepository
    {
        void add(Attraction atr);
        Attraction getById(Guid Id);
        Attraction getByName(string Name);
        IEnumerable<Attraction> getAll();
        void update(Attraction atr);
        void delete(Attraction atr);
    }
}
