using System;

namespace TouristAttractions.API.DataContracts.Requests
{
    public class AttractionCreationRequest
    {
        public DateTime Date { get; set; }

        public Attraction Attraction { get; set; }
    }
}
