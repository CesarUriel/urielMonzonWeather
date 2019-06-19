  function londonBtnFun() {
    clear();
    $.ajax({
      type: 'get',
      url: "http://localhost:8081/app/weather/London",
      success: function(data) {
       fillFields(data);
     },
     error: function(xhr, status, error) {
       manageError(xhr);
     }
    });
   }

   function HongKongBtnFun() {
     clear();
     $.ajax({
       type: 'get',
       url: "http://localhost:8081/app/weather/Hongkong",
       success: function(data) {
        fillFields(data);
      },
      error: function(xhr, status, error) {
        window.location.href = 'error.html';
        var err = eval("(" + xhr.responseText + ")");
        console.log(err.Message);
      }
    });
    }

    function clear(){
      $('.currentDate').empty();
      $('.cityName').empty();
      $('.description').empty();
      $('.tempF').empty();
      $('.tempC').empty();
      $('.sunrise').empty();
      $('.sunset').empty();
    }

    function fillFields(data){
      $('.currentDate').append('Current date: ').append(data.currentDate);
      $('.cityName').append('City name: ').append(data.cityName);
      $('.description').append('Description: ').append(data.description);
      $('.tempF').append('Fahrenheit: ').append(data.tempF);
      $('.tempC').append('Centigrades: ').append(data.tempC);
      $('.sunrise').append('Sunrise time: ').append(data.sunrise);
      $('.sunset').append('Sunset time: ').append(data.sunset);
    }

    function manageError(xhr){
      window.location.href = 'error.html';
      var err = eval("(" + xhr.responseText + ")");
      console.log(err.Message);
    }
