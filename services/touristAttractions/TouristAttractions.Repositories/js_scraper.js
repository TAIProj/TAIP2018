JSON.stringify({Name: document.querySelector('.entry-title').innerText,
Description: document.querySelector('.post-content p').innerText,
imageURL: document.querySelector('.tp-bgimg.defaultimg').style.backgroundImage.replace('url("', '').replace('")', ''),
Address: {
	City: "Iaşi",
	Street: "-",
	ZipCode: "-",
	Country: "Romania"
}})