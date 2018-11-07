using System;
using System.Linq;
using System.Collections.Generic;
using TouristAttractions.Services.Contracts;
using TouristAttractions.API.DataContracts;

namespace TouristAttractions.Repositories
{
    public class AttractionRepository : IAttractionRepository
    {
        private AttractionsDbContext _context;

        public AttractionRepository(AttractionsDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));

            if (context.Attractions.Count() == 0)
            {
                seed();
            }
        }

        public void add(Attraction atr)
        {
            _context.Attractions.Add(atr);
            _context.SaveChanges();
        }

        public void delete(Attraction atr)
        {
            _context.Attractions.Remove(atr);
            _context.SaveChanges();
        }

        public IEnumerable<Attraction> getAll()
        {
            return _context.Attractions.ToArray();
        }

        public Attraction getById(Guid Id)
        {
            return _context.Attractions.Where(x => x.Id == Id).FirstOrDefault();
        }

        public Attraction getByName(string Name)
        {
            return _context.Attractions.Where(x => x.Name == Name).FirstOrDefault();
        }

        public void update(Attraction atr)
        {
            _context.Attractions.Attach(atr);
            _context.SaveChanges();
        }

        private void seed()
        {
            var list = new DbSeeder().getFromJson();
            foreach (var attr in list)
            {
                attr.Address = _context.Addresses.Where(x => x.Street == attr.Address.Street).FirstOrDefault();
                add(attr);
            }
        }
    }
}
