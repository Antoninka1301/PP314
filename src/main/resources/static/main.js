async function getUsers() {

    const response = await fetch("http://localhost:8080/admin/users");

    if (response.ok) {
        let json = await response.json()
            .then(data => replaceTable(data));
    } else {
        alert("Ошибка HTTP: " + response.status);
    }

    function replaceTable(data) {

        const placement = document.getElementById("table-body")
        placement.innerHTML = "";
        data.forEach(({id, username, name, email, roles}) => {
            let userRoles = "";
            roles.forEach((role) => {
                userRoles = userRoles + role.name + " ";
            })
            const element = document.createElement("tr");
            element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${username}</td>
            <td>${name}</td>
            <td>${email}</td>
            <td>${userRoles}</td>
            <td>
                <button type="button" class="btn btn-info text-white" data-bs-userId=${id}
                    data-bs-userName=${username} data-bs-username=${name}
                    data-bs-userEmail=${email} data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-userId=${id}
                    data-bs-userName=${username} data-bs-username=${name}
                    data-bs-userEmail=${email} data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>
            `
            placement.append(element);
        })
    }
}

window.onload = () => getUsers()


document.getElementById("newUserForm")
    .addEventListener("submit", addNewUser);

function addNewUser() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let name = document.getElementById('name').value;
    let email = document.getElementById('email').value;
    let roles = getRoles(Array.from(document.getElementById('newRole').selectedOptions)
        .map(r => r.value));
    fetch("http://localhost:8080/admin/users", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            name: name,
            email: email,
            roles: roles
        })
    })
        .then(() => {
            document.getElementById("table-body").click();
            getUsers();
            document.getElementById("newUserForm").reset();
        })
}
function getRoles(list) {
    let roles = [];
    if (list.indexOf("USER") >= 0) {
        roles.push({"id": 1});
    }
    if (list.indexOf("ADMIN") >= 0) {
        roles.push({"id": 2});
    }
    return roles;
}