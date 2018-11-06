using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace TouristAttractions.API.DataContracts
{
    public class Address
    {
        [Key]
        public Guid Id { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string City { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string Street { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string ZipCode { get; set; }

        [Required]
        [DataType(DataType.Text)]
        public string Country { get; set; }
    }
}
