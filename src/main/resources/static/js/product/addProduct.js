function addProduct() {
    if($('#url').val() == '') {
        alert('link product can not be blank.')
    } else {
     $.post("/api/product/add",{url:$('#url').val()}, function(data){
            if(data.status==600){
              window.location = "/list-products";
            }else{
              console.log("not");
        }
        });
    }
}
