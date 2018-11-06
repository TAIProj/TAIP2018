using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;
using TouristAttractions.API.DataContracts;

namespace TouristAttractions.Repositories
{
    public class DbSeeder
    {
        private const string fileName = "input.json";

        public List<Attraction> getFromJson()
        {
            var dataText = System.IO.File.ReadAllText(fileName);
            return JsonConvert.DeserializeObject<List<Attraction>>(dataText);
        }
    }
}
