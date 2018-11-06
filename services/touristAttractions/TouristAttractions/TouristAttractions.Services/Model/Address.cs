using System;
using System.Collections.Generic;
using System.Text;

namespace TouristAttractions.Services.Model
{
    public class Address
    {
        public Guid Id { get; set; }
        public string City { get; set; }
        public string Street { get; set; }
        public string ZipCode { get; set; }
        public string Country { get; set; }
    }
}
