$(document).ready(function(){
    $(".ww-like").click(function(){
        var id=$(this).closest("[data-id]").attr("data-id");
        var url= `/product/like/${id}`;
        $.getJSON(url).then(resp=>{
            alert("so luot like hien tai: "+resp)
        })

    });

    $(".btn-share").click(function(){
        data_id=$(this).closest("[data-id]").attr("data-id");
        console.log(data_id)
    });

    $(".btn-send").click(function(){
        var data={
            id: data_id,
            email: $("#email").val()
        }
        console.log(data);
        var url= `/product/send`;
        $.post(url,data).then(resp=>{
            alert(resp)
        })
   })
    
})
