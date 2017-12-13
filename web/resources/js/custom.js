/* Nav Change */
$(window).scroll(function() {
  var scroll = $(window).scrollTop();

  if (scroll >= 80) {
    $(".navbar-brand img").removeClass("img-brand");
    $(".navbar-brand img").addClass("new-brand");

    $("nav").removeClass("transparent");
    $("nav").addClass("nav-flat");
    $("nav").addClass("fixed-top");

  } else {
    $(".navbar-brand img").removeClass("new-brand");
    $(".navbar-brand img").addClass("img-brand");

    $("nav").removeClass("nav-flat");
    $("nav").addClass("transparent");
    $("nav").removeClass("fixed-top");
  }
});


/* Search Table */
$(document).ready(function() {
  $('#search-cliente').keyup(function() {
    searchCliente($(this).val());
  });
  function searchCliente(value) {
    $('#table-cliente tbody tr').each(function() {
      var found = 'false';
      $(this).each(function() {
        if ($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
        {
          found = 'true';
        }
      });
      if (found === 'true')
      {
        $(this).show();
      } else
      {
        $(this).hide();
      }
    });
  }
});

$(document).ready(function() {
  $('#search-fornecedor').keyup(function() {
    searchFornecedor($(this).val());
  });
  function searchFornecedor(value) {
    $('#table-fornecedor tbody tr').each(function() {
      var found = 'false';
      $(this).each(function() {
        if ($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0)
        {
          found = 'true';
        }
      });
      if (found === 'true')
      {
        $(this).show();
      } else
      {
        $(this).hide();
      }
    });
  }
});


