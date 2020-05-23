$(document).ready(function() {
    $.get("/api/product/all",{}, function(data){
        if(data.status==600){
           $.each(data.data, function(i, product){
               var customerRow = '<tr>' +
                                   '<td>' + ++i + '</td>' +
                                   '<td>' + product.name+ '</td>' +
                                   '<td>' + product.description + '</td>' +
                                   '<td>' + product.current_price + '</td>' +
                                   '<td>' + product.url + '</td>' +
                                   '<td> <a href="/detail-product?id=' + product.id + '" class="btn btn-primary '+
                                   'btn-md marginTB-md m-right-sm data-toggle="tooltip" data-placement="top" '+
                                   'title="View"> <span class="fa fa-eye fa-sm">Detail</span></a></td>' +
                                   '</tr>';

                         $('#productTable tbody').append(customerRow);
               });

               $( "#productTable tbody tr:odd" ).addClass("info");
                $( "#productTable tbody tr:even" ).addClass("success");

           }
        else {
            alert('Sorry. The API of this system seems in trouble.');
        }

        });
 });