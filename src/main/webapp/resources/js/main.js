/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global fetch */

function addToCart(id, image, name, price) {
    fetch('/instrumentStore/api/cart', {
        method: 'post',
        body: JSON.stringify({
            "productId": id,
            "productImage": image,
            "productName": name,
            "productPrice": price,
            "quantity": 1
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        return res.json()
    }).then(function(data) {
        let counter = document.getElementById('cartCounter')
        counter.innerText = data
    }).then(function() {
        alert("Một sản phẩm vừa được thêm vào giỏ hàng")
    })
}

function updateCart(obj, productId) {
    fetch('/instrumentStore/api/cart', {
        method: 'put',
        body: JSON.stringify({
            "productId": productId,
            "productImage": "",
            "productName": "",
            "productPrice": 0,
            "quantity": obj.value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        return res.json()
    }).then(function(data) {
        let counter = document.getElementById('cartCounter')
        counter.innerText = data
        cartSubTotal()
    })
}

function cartSubTotal() {
    fetch('/instrumentStore/api/cartSubTotal', {
        method: 'put',
        body: JSON.stringify({
            "subTotal": 0
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        return res.json()
    }).then(function(data) {
        let subTotal = document.getElementById('subTotal')
        subTotal.innerText = numberWithCommas(data)
    })
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function deleteCartItem(productId) {
    fetch('/instrumentStore/api/cart/'+productId, {
        method: "delete",
        body: JSON.stringify({}),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res) {
        return res.json()
    }).then(function(data) {
        let counter = document.getElementById('cartCounter')
        counter.innerText = data
        cartSubTotal()
        let row = document.getElementById('product'+productId)
        row.style.display = "none"
    })
}
