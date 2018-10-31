using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TouristAttractions.API.DataContracts.Requests;
using TouristAttractions.API.DataContracts;
using TouristAttractions.Services;
using AutoMapper;
using S = TouristAttractions.Services.Model;
using TouristAttractions.Services.Contracts;

namespace TouristAttractions.API.Controllers
{
    [ApiVersion("1.0")]
    [Route("api/attractions")]
    [Route("api/v{version:apiVersion}/attractions")]
    public class AttractionController : Controller
    {
        private readonly IAttractionService _service;
        private readonly IMapper _mapper;

        public AttractionController(IAttractionService service, IMapper mapper)
        {
            _service = service;
            _mapper = mapper;
        }

        #region GET
        [HttpGet("{id}")]
        public async Task<Attraction> Get(string id)
        {
            var data = await _service.GetAsync(id);

            if (data != null)
                return _mapper.Map<Attraction>(data);
            else
                return null;
        }
        #endregion

        #region POST
        [HttpPost]
        public async Task<Attraction> CreateAttraction([FromBody]AttractionCreationRequest value)
        {

            //TODO: include exception management policy according to technical specifications
            if (value == null)
                throw new ArgumentNullException("value");


            var data = await _service.CreateAsync(Mapper.Map<S.Attraction>(value));

            if (data != null)
                return _mapper.Map<Attraction>(data);
            else
                return null;

        }
        #endregion

        #region PUT
        [HttpPut()]
        public async Task<bool> UpdateAttraction(Attraction parameter)
        {
            if (parameter == null)
                throw new ArgumentNullException("parameter");

            return await _service.UpdateAsync(Mapper.Map<S.Attraction>(parameter));
        }
        #endregion

        #region DELETE
        [HttpDelete("{id}")]
        public async Task<bool> DeleteDevice(string id)
        {
            return await _service.DeleteAsync(id);
        }
        #endregion
    }
}
