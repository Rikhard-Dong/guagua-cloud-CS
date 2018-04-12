$(document).ready(function(){
$("#experice").click(function(){
  if(document.getElementById("experice").value=="经验降序")
  {
    document.getElementById('experice').value='经验升序';
  }
  else {
    document.getElementById('experice').value='经验降序';
  }
});

$("#register").click(function(){
    if(document.getElementById("register").value=="注册时间降序")
    {
      document.getElementById('register').value='注册时间升序';
    }
    else {
      document.getElementById('register').value='注册时间降序';
    }
  });
  });