<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setAttribute("root", request.getContextPath()); %>
<style>
    .autocomplete-suggestions {border: 1px solid #999; background: #FFF; overflow: auto;}
    .autocomplete-suggestion {padding: 2px 5px; white-space: nowrap; overflow: hidden;}
    .autocomplete-selected {background: #F0F0F0; }
    .autocomplete-suggestions strong {font-weight: normal; color: #3399FF;}
    .autocomplete-group {padding: 2px 5px; }
    .autocomplete-group strong {display: block; border-bottom: 1px solid #000;}
</style>
<script src="${root}/resources/vendor/jquery/jquery.autocomplete.js"></script>