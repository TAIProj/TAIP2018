using System;
using System.Linq;
using TouristAttractions.API.DataContracts;
using TouristAttractions.Services.Contracts;

namespace TouristAttractions.Repositories
{
    public class AddressRepository : IAddressRepository
    {
        private AttractionsDbContext _context;

        public AddressRepository(AttractionsDbContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context));

            if (_context.Addresses.Count() == 0)
            {
                seed();
            }
        }

        public void add(Address address)
        {
            _context.Addresses.Add(address);
            _context.SaveChanges();
        }

        public Address getById(Guid Id)
        {
            return _context.Addresses.Where(x => x.Id == Id).FirstOrDefault();
        }

        private void seed()
        {
            var list = new DbSeeder().getFromJson();
            foreach (var attr in list)
            {
                add(attr.Address);
            }
        }
    }
}
