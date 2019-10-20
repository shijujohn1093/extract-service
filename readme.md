https://www.smashingmagazine.com/2018/02/sse-websockets-data-flow-http2/
https://howtodoinjava.com/spring-boot2/rest/spring-async-controller-responsebodyemitter/

# Async node client

````

var http = require('http');
const querystring = require('querystring');
const parameters = {
	profileId: '123'
}
const get_request_args = querystring.stringify(parameters);

http.get({ agent : false,
  url : 'http://localhost',
  port : '8081',
  path : '/extract/rbe?' + get_request_args   
  }, (response) => {
    console.log(JSON.stringify(response.headers));
    response.on('data', data => {
      console.log(data.toString());
    })
  }
);

````