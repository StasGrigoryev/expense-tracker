<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jspl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<%@include file="../jsp/head.jsp"%>
<body>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<%@include file="../jsp/menu.jsp"%>
		<main class="mdl-layout__content">
			<div class="page-content">
				<div class="mdl-grid center-items">
					<div class="mdl-cell mdl-cell--4-col">
						<div class="mdl-card mdl-shadow--6dp">
							<div
								class="mdl-card__title mdl-color--primary mdl-color-text--white">
								<h2 class="mdl-card__title-text">
									<c:if test="${transaction != null}">Edit Transaction</c:if>
									<c:if test="${transaction == null}">Add New Transaction</c:if>
								</h2>
							</div>
							<div class="mdl-card__supporting-text">
								<c:if test="${transaction != null}">
									<form name="myForm" action="update" method="post" onsubmit="return validateForm()">
								</c:if>
								<c:if test="${transaction == null}">
									<form name="myForm" action="insert" method="post" onsubmit="return validateForm()">
								</c:if>  
								<c:if test="{$transaction != null}">
									<input type="hidden" name="id" 
									value="<c:out value='${transaction.id}' />" /> 
								</c:if>
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text" name="cost"
										value="<c:out value='${transaction.cost}' />" id="cost" /> 
										<label class="mdl-textfield__label" for="cost">Cost</label>
								</div>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text"
									name="date"
									value="<c:out value='${transaction.date}' />" id="date" />
									<label class="mdl-textfield__label" for="date">Date</label>
								</div>


<!--just for knowing how to use these tags -->
								<!-- 
								<div class="mdl-textfield mdl-js-textfield">
								<c:choose>
								<c:when test = "${transaction != null}">
								<input class="mdl-textfield__input" type="text" name="quantity" value="<c:out value='${transaction.quantity}' />" id="quantity" /> 
								</c:when>
								<c:otherwise>
								<input class="mdl-textfield__input" type="text" name="quantity" value="<c:out> value='1' />" 
								id="quantity" />
								</c:otherwise>
								</c:choose>
								<label class="mdl-textfield__label" for="quantity">Quantity</label> -->

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text"
									name="expense_id"
									value="<c:out value='${transaction.expenseId}' />" id="expense_id" />
									<label class="mdl-textfield__label" for="expense_id">Expense</label>
								</div>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text"
									name="expense_category_id"
									value="<c:out value='${transaction.expenseCategoryId}' />" id="expense_category_id" />
									<label class="mdl-textfield__label" for="expense_category_id">Expense category</label>
								</div>

								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" type="text"
									name="comment"
									value="<c:out value='${transaction.comment}' />" id="comment" />
									<label class="mdl-textfield__label" for="comment">Comment</label>
								</div>
								<input type="submit"
								class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
								value="save">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>



















									

