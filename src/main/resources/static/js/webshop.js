$(document).ready(function () {
    $(".ww-like").click(function () {
        var id = $(this).closest("[data-id]").attr("data-id");
        var url = `/product/like/${id}`;
        $.getJSON(url).then(resp => {
            alert("so luot like hien tai: " + resp)
        })

    });

    $(".btn-share").click(function () {
        data_id = $(this).closest("[data-id]").attr("data-id");
        console.log(data_id)
    });

    $(".btn-send").click(function () {
        var data = {
            id: data_id,
            email: $("#email").val()
        }
        console.log(data);
        var url = `/product/send`;
        $.post(url, data).then(resp => {
            alert(resp)
        })
    });
    function showCartInfo() {
        var url = "/api/cart/info";
        $.getJSON(url).then(resp => {

            $("#cart-count").html(resp.count);
            $("#total-number-products").html(resp.count);
            
            $("#cart-amt").html(resp.amount.toFixed(2));
            $(".total-amount-checkout").html(resp.amount.toFixed(2));
        })
    };
    showCartInfo();

    $(".btn-cart-add").click(function () {
        var id = $(this).closest("[data-id]").attr("data-id");
        var url = `/api/cart/add/${id}`;
        $.getJSON(url).then(resp => {
            showCartInfo();
        });
    });

    $(".btn-cart-remove-item").click(function () {
        var id = $(this).closest("[data-id]").attr("data-id");
        var url = `/api/cart/remove/${id}`;
        $.getJSON(url).then(resp => {
            showCartInfo();
            var id = $(this).closest("[data-id]").hide(300);
        });

    });

    $(".cart-update").click(function () {
        var id = $(this).closest("[data-id]").attr("data-id");
        var price = $(this).closest("[data-id]").attr("data-price");
        var discount = $(this).closest("[data-id]").attr("data-discount");
        var qty=$(this).val();
        var url = `/api/cart/update/${id}/${qty}`;

        $.getJSON(url).then(resp => {
            showCartInfo();  
            var amt=price*qty*(1-discount).toFixed(2);
            
            $(this).closest("[data-id]").find(".item-amount").html(amt);
        });
        
    });

  
});

