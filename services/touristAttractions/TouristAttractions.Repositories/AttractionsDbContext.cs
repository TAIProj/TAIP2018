
using Microsoft.EntityFrameworkCore;
using TouristAttractions.API.DataContracts;

namespace TouristAttractions.Repositories
{
    public class AttractionsDbContext : DbContext
    {
        public AttractionsDbContext(DbContextOptions<AttractionsDbContext> options)
        : base(options)
            { }

        public DbSet<Attraction> Attractions{ get; set; }
        public DbSet<Address> Addresses { get; set; }
    }
}
