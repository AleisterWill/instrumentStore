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
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
        let counter = document.getElementById('cartCounter')
        counter.innerText = data
    }).then(function () {
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
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
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
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
        let subTotal = document.getElementById('subTotal')
        subTotal.innerText = numberWithCommas(data)
    })
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function deleteCartItem(productId) {
    fetch('/instrumentStore/api/cart/' + productId, {
        method: "delete",
        body: JSON.stringify({}),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
        let counter = document.getElementById('cartCounter')
        counter.innerText = data
        cartSubTotal()
        let row = document.getElementById('product' + productId)
        row.style.display = "none"
    })
}

function deleteImgPath(imgPathId) {
    fetch('/instrumentStore/api/imgPath/' + imgPathId, {
        method: "delete",
        body: JSON.stringify({}),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json()
    }).then(function (data) {
        if (data === true) {
            alert('Một hình ảnh đã được xóa khỏi CSDL')
            let row = document.getElementById(imgPathId)
            row.style.display = "none"
        } else {
            alert('Đã có lỗi xảy ra')
        }
    })
}

function deleteImgSet(imgSetId) {
    if (confirm('Điều này sẽ xóa tất cả ảnh của bộ ảnh!\n Bạn vẫn chắc chắn muốn xóa?')) {
        fetch('/instrumentStore/api/imgSet/' + imgSetId, {
            method: "delete",
            body: JSON.stringify({}),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json();
        }).then(function (data) {
            if (data === true) {
                alert('Một bộ ảnh đã được xóa khỏi CSDL');
                let row = document.getElementById(imgSetId);
                row.style.display = "none";
            } else {
                alert('Đã có lỗi xảy ra');
            }
        });
    }
}

function deleteProduct(productId) {
    if (confirm('Bạn có chắc chắn muốn xóa sản phẩm này khỏi CSDL?')) {
        fetch('/instrumentStore/api/product/' + productId, {
            method: "delete",
            body: JSON.stringify({}),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function (res) {
            return res.json();
        }).then(function (data) {
            if (data === true) {
                alert('Một sản phẩm đã được xóa khỏi CSDL');
                let row = document.getElementById(productId);
                row.style.display = "none";
            } else {
                alert('Đã có lỗi xảy ra');
            }
        });
    }
}

function insertRow() {
    var addRow = document.getElementById('addRow');
    var btnAdd = document.getElementById('btnAdd');
    var btnSave = document.getElementById('btnSave');
    var btnCancel = document.getElementById('btnCancel');

    addRow.setAttribute("class", "");
    btnAdd.setAttribute("disabled", "true");
    btnSave.setAttribute("class", "btn btn-success mt-1 p-2");
    btnCancel.setAttribute("class", "btn btn-secondary");
}

function cancel() {
    var addRow = document.getElementById('addRow');
    var btnAdd = document.getElementById('btnAdd');
    var btnSave = document.getElementById('btnSave');
    var btnCancel = document.getElementById('btnCancel');

    addRow.setAttribute("class", "d-none");
    btnAdd.removeAttribute("disabled");
    btnSave.setAttribute("class", "d-none");
    btnCancel.setAttribute("class", "d-none");
}
