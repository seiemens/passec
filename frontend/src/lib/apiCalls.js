import {setAuthHeader} from "$lib/loginHelper.js";

export const apiURL = "http://localhost:8080";

export async function createUser(username, password) {
    return await fetch(apiURL + '/user/create', {
        method: 'POST', headers: {
            'content-type': 'application/json'
        }, body: JSON.stringify({"username": username, "password": password})
    });
}

export async function getSelfUser() {
    return await fetch(apiURL + '/user/self', {
        method: 'GET', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }
    });
}

export async function getAllUsers() {
    return await (await fetch(apiURL + '/user/all', {
        method: 'GET', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }
    })).json();
}

export async function deleteUser(id) {
    return await fetch(apiURL + '/user/' + id, {
        method: 'DELETE', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }
    });
}

export async function getPastes() {
    return await (await fetch(apiURL + '/paste/all', {
        method: 'GET', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }
    })).json();
}

export async function getPasteById(id) {
    return await (await fetch(apiURL + '/paste/view/' + id, {
        method: 'GET', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }
    })).json();
}

export async function createPaste(title, content) {
    return await (await fetch(apiURL + '/paste/create', {
        method: 'POST', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }, body: JSON.stringify({"title": title, "content": content})

    })).json();
}

export async function editPaste(id, title, content) {
    return await (await fetch(apiURL + '/paste/edit/' + id, {
        method: 'POST', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }, body: JSON.stringify({"title": title, "content": content})

    })).json();
}

export async function deletePaste(id) {
    return await fetch(apiURL + '/paste/' + id, {
        method: 'DELETE', headers: {
            'content-type': 'application/json', 'authorization': setAuthHeader()
        }
    });
}