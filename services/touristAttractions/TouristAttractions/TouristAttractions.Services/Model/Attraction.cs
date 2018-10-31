using System;
using System.Collections.Generic;
using System.Text;

namespace TouristAttractions.Services.Model
{
    public class Attraction
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string ImageURL { get; set; }
        public Adress Adress { get; set; }
    }
}
