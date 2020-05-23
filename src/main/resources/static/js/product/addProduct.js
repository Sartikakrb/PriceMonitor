function addProduct() {
    if($('#url').val() == '') {
        alert('link product can not be blank.')
    } else {
        if($('#url').val().indexOf("fabelio") > -1) {
             $("#infos").show();
            $.post("/api/product/add",{url:$('#url').val()}, function(data){
                        if(data.status==600){
                          alert('your request has been process. wait a while');
                          window.location = "/list-products";
                        }else{
                          console.log("not");
                         }
              });
        } else {
            alert('fill product link from fabelio website');
        }
    }
}
