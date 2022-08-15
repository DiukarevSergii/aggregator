curl --location --request POST 'http://localhost:8080/api/v1/extract' \
--header 'Content-Type: application/json' \
--data-raw '[
    "https://www.bbc.com",
    null,
    "",
    "https://www.radiosport.co.nz/sport-news/rugby/accident-or-one-last-dig-eddie-jones-reveals-hansens-next-job/",
    "https://www3.forbes.com/business/2020-upcoming-hottest-new-vehicles/13/?nowelcome",
    "https://www.tvblog.it/post/1681999/valerio-fabrizio-salvatori-gli-inseparabili-chi-sono-pechino-express-2020"
]'