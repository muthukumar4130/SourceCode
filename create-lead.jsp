<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<link href="resources/css/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>


<script>
	bkLib.onDomLoaded(function() {
		new nicEditor({
			buttonList : [ 'fontSize', 'bold', 'italic', 'underline', 'ol',
					'ul', 'strikeThrough', 'html' ]
		}).panelInstance('inputAddress');
	});
</script>
<style>
 label {
 	margin-left: 0px;
 }
</style>
<div class="row scrollspy-sidenav pb-20 body-mt-15">
	 <script>
	$(document).ready(function() {

		$('#btnsubmit').click(function(e) {
			var isValid = true;
			
			var leadOwner = $('#leadOwner').val();
			if (leadOwner == '0') {
				isValid = false;
				$("#leadOwnerErr").show();
				$("#leadOwnerErr").html("Please enter lead Owner");
				$("#leadOwner").css({
					"border" : "1px solid red",

				});

			} else {
				$('#leadOwnerErr').hide();
				$('#leadOwner').css({

					"border" : "",
					"background" : ""
				});
			}
			
			var firstName = $('#firstName').val();
			if (firstName == '') {
				isValid = false;
				$("#firstNameErr").show();
				$("#firstNameErr").html("Please enter firstName");
				$("#firstName").css({
					"border" : "1px solid red",

				});

			} else {
				$('#firstNameErr').hide();
				$('#firstName').css({

					"border" : "",
					"background" : ""
				});
			}
			
			
			
			var lastName = $('#lastName').val();
			if (lastName == '') {
				isValid = false;
				$("#lastNameErr").show();
				$("#lastNameErr").html("Please enter lastName");
				$("#lastName").css({
					"border" : "1px solid red",

				});

			} else {
				$('#lastNameErr').hide();
				$('#lastName').css({

					"border" : "",
					"background" : ""
				});
			}
			
			var state = $('#state').val();
			if (state == '') {
				isValid = false;
				$("#stateErr").show();
				$("#stateErr").html("Please enter state");
				$("#state").css({
					"border" : "1px solid red",

				});

			} else {
				$('#stateErr').hide();
				$('#state').css({

					"border" : "",
					"background" : ""
				});
			}
			
			
			
			var leadStatus = $('#leadStatus').val();
			if (leadStatus == '') {
				isValid = false;
				$("#leadStatusErr").show();
				$("#leadStatusErr").html("Please fill leadStatus");
				$("#leadStatus").css({
					"border" : "1px solid red",

				});

			} else {
				$('#leadStatusErr').hide();
				$('#leadStatus').css({

					"border" : "",
					"background" : ""
				});
			}			

 	
			var mobile = $('#mobile').val();
			if (mobile == '0') {
				isValid = false;
				$("#mobileErr").show();
				$("#mobileErr").html("Please enter mobile number");
				$("#mobile").css({
					"border" : "1px solid red",

				});

			} else {
				$('#mobileErr').hide();
				$('#mobile').css({

					"border" : "",
					"background" : ""
				});
				changefunctions()
			}			

					
			var company = $('#company').val();
			if (company == '') {
				isValid = false;
				$("#companyErr").show();
				$("#companyErr").html("Please enter company");
				$("#company").css({
					"border" : "1px solid red",

				});

			} else {
				$('#companyErr').hide();
				$('#company').css({

					"border" : "",
					"background" : ""
				});
			}		
			
		
			
			var emailId = $('#emailId').val();
			if ( emailId == '') {
				isValid = false;
				$("#emailIdErr").show();
				$("#emailIdErr").html("Please enter emailId");
				$("#emailId").css({
					"border" : "1px solid red",

				});

			} else {
				$('#emailIdErr').hide();
				$('#emailId').css({

					"border" : "",
					"background" : ""
				});
				changefunction()
			}

				

			var city = $('#city').val();
			if ( city == '') {
				isValid = false;
				$("#cityErr").show();
				$("#cityErr").html("Please enter city");
				$("#city").css({
					"border" : "1px solid red",

				});

			} else {
				$('#cityErr').hide();
				$('#city').css({

					"border" : "",
					"background" : ""
				});
			}		

			var country = $('#country').val();
			if ( country == '') {
				isValid = false;
				$("#countryErr").show();
				$("#countryErr").html("Please enter country");
				$("#country").css({
					"border" : "1px solid red",

				});

			} else {
				$('#countryErr').hide();
				$('#country').css({

					"border" : "",
					"background" : ""
				});
			}						

			var annualRevenue = $('#annualRevenue').val();
			if ( annualRevenue == '0') {
				isValid = false;
				$("#annualRevenueErr").show();
				$("#annualRevenueErr").html("Please enter annualRevenue");
				$("#annualRevenue").css({
					"border" : "1px solid red",

				});

			} else {
				$('#annualRevenueErr').hide();
				$('#annualRevenue').css({

					"border" : "",
					"background" : ""
				});
			}				
			var leadSource = $('#leadSource').val();
			if (leadSource == '0') {
				isValid = false;
				$("#leadSourceErr").show();
				$("#leadSourceErr").html("Please enter leadSource");
				$("#leadSource").css({
					"border" : "1px solid red",

				});

			} else {
				$('#leadSourceErr').hide();
				$('#leadSource').css({

					"border" : "",
					"background" : ""
				});
			}		

			if (isValid == false)
				e.preventDefault();	
			
		});
	});
</script> 

<script type="text/javascript">

function changefunction(){
    var emailId=document.getElementById("emailId").value;

    if(emailId !=''){
  	  $.ajax({
  			type	: "GET",
  			url		: "email-check",
  			data	: "emailId=" + emailId,

  			success  : function(data){

  				if(data == true){
  					emailId=true;
  					$("#emailIdErr").html("Email Already Exist");
  					$("#emailIdErr").show();
  					}
  				else{
  					emailId =false;
  					$("#emailIdErr").hide();
  					}
  				}


  			});

        }
    };

     function changefunctions(){
    var mobile=document.getElementById("mobile").value;

    if(mobile !=''){
  	  $.ajax({
  			type	: "GET",
  			url		: "mobile-check",
  			data	: "mobile=" + mobile,

  			success  : function(data){

  				if(data == true){
  					mobile=true;
  					$("#mobileErr").html("mobileNumber Already Exist");
  					$("#mobileErr").show();
  					}
  				else{
  					mobile =false;
  					$("#mobileErr").hide();
  					}
  				}


  			});

        }
    }; 

</script>

	<div class="warning">
	</div>
		<div class="contact-form-wrapper">

		<div class="box-list">
			<div class="item">
				<div class="row ">

					<%-- <c:if test="${functionType eq 'add'}"> --%>
						<div class="text-center underline">
							<h3>Create lead</h3>
						</div>
						<br>
						<form:form method="POST" id="addForm" action="create-lead"
							modelAttribute="leadObj">
						<div class="col-sm-12">
								<div class="col-sm-4">
									<div class="form-group">
										<label> firstName <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" id="firstName" path="firstName"
											class="form-control required" placeholder="firstName"
											maxlength="150" />
										<form:errors path="firstName" class="error" />
										<div id="firstNameErr" style="color: red;"></div>
									</div>
								</div>
								
								<div class="col-sm-4">
									<div class="form-group">
										<label> lastname <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" id="lastName" path="lastName"
											class="form-control required" placeholder="lastname"
											maxlength="150" />
										<form:errors path="lastName" class="error" />
										<div id="lastNameErr" style="color: red;"></div>
									</div>
								</div>
								
								<div class="col-sm-4">
									<div class="form-group">
										<label>emailId <span
											class="font10 text-danger">*</span></label>
										<form:input type="email" id="emailId" path="emailId" name="emailId" value=""
											class="form-control" placeholder="emailId" onchange="changefunction()"
											maxlength="150" />
										<form:errors path="emailId" class="error" />
										<div id="emailIdErr" style="color: red;"></div>
										<!-- <div id="erroremailId" style="color: red;"></div> -->	
									</div>
								</div>
                              </div>
                              
                             <div class="col-sm-12">
                              <div class="col-sm-4">
									<div class="form-group">
										<label> leadStatus <span
											class="font10 text-danger">*</span></label>
										<form:select type="text" path="leadStatus" id="leadStatus"
											class="form-control required">
											<form:option value="">Select Status</form:option>
											<form:option value="active">ACTIVE</form:option>
											<form:option value="inactive">INACTIVE</form:option>
										</form:select>
                                      <form:errors path="leadStatus" class="error" /> 
										<div id="leadStatusErr" style="color: red;"></div>									
									</div>
								</div>				
								
								  <div class="col-sm-4">
									<div class="form-group">
										<label> leadSource <span
											class="font10 text-danger">*</span></label>
										 <form:select type="text" path="campaign.campaignId" id="leadSource"
											class="form-control required">
											 <form:option value="0">Select campaign Name</form:option> 
											 <form:options items="${campaignList}" itemLabel="campaignName"
												itemValue="campaignId"/> 
										</form:select> 
                                        <%-- <form:errors path="leadSource" class="error" />  --%>
										<div id="leadSourceErr" style="color: red;"></div>									
									</div>
								</div>		 
								<div class="col-sm-4">
									<div class="form-group">
										<label> mobile <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="mobile" onchange="changefunctions()"
											id="mobile" class="form-control required"
											placeholder="" maxlength="10" />
										<form:errors path="mobile" class="error" />
										<div id="mobileErr" style="color: red;"></div>

									</div>
								</div>
						</div>
						<div class="col-sm-12">
	                            <div class="col-sm-4">
									<div class="form-group">
										<label> company <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="company" id="company"
											class="form-control required" placeholder="company"
											maxlength="10" />
										<form:errors path="company" class="error" />
										<div id="companyErr" style="color: red;"></div>

									</div>
								</div>								
                               <div class="col-sm-4">
									<div class="form-group">
										<label> city <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="city" id="city"
											class="form-control required" placeholder="City"
											maxlength="10" />
										<form:errors path="city" class="error" />
										<div id="cityErr" style="color: red;"></div> 

									</div>
								</div>							
							
							 <div class="col-sm-4">
									<div class="form-group">
										<label> annualRevenue <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="annualRevenue" id="annualRevenue"
											class="form-control required" placeholder="annualRevenue"
											maxlength="10" />
										<form:errors path="annualRevenue" class="error" />
										<div id="annualRevenueErr" style="color: red;"></div> 
								</div>
							</div>					
						</div>
						<div class="col-sm-12">
							 <div class="col-sm-4">
									<div class="form-group">
										<label> state <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="state" id="state"
											class="form-control required" placeholder="state"
											maxlength="10" />
										 <form:errors path="state" class="error" />
										<div id="stateErr" style="color: red;"></div> 
 
									</div>
								</div>							
						
							
							
							 <div class="col-sm-4">
									<div class="form-group">
										<label> country <span
											class="font10 text-danger">*</span></label>
										<form:input type="text" path="country" id="country"
											class="form-control required" placeholder="country"
											maxlength="10" />
										<%--  < <form:errors path="country" class="error" /> --%>
										<div id="countryErr" style="color: red;"></div> 
									</div>
								</div>
									
						 <div class="col-sm-4">
									<div class="form-group">
										<label> leadOwner <span
											class="font10 text-danger">*</span></label>
											<form:select type="text" id="leadOwner" path="user.id"
											class="form-control required">
											 <form:option value="0">Select Employee Name</form:option> 
											<form:options items="${userBOList}" itemLabel="name"
												itemValue="id"/>
										</form:select>
										<%-- <form:errors path="leadOwner" class="error" /> --%>
										<div id="leadOwnerErr" style="color: red;"></div>

									</div>
								</div> 											
							</div>	
				<div style="text-align: right; margin-right: 31px">
									<button type="submit" id="btnsubmit"
									class="btn btn-t-primary btn-theme lebal_align mt-20"
									style="text-align: right;">Submit</button>
								<a href="admin-home"><span
									class="btn btn-t-primary btn-theme lebal_align mt-20">Cancel</span></a>
							</div>
						</form:form>
						
				<%-- 	</c:if> --%>

				</div>
			</div>
		</div>
	</div>
</div>
<br>
