using System;
using System.ComponentModel.DataAnnotations;

namespace TouristAttractions.API.DataContracts
{
    public class Attraction
    {
        [Key]
        public Guid Id { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string Name { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string Description { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string ImageURL { get; set; }

        public Address Address { get; set; }
    }
}
