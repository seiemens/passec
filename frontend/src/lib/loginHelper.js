import {isAdmin, isLoggedIn} from "$lib/stores.js";
import {getSelfUser} from "$lib/apiCalls.js";

export async function checkSignIn() {
    let res = await getSelfUser().then((res) => {
        if (res.status !== 200) {
            return false;
        }
        return res.json().then((j) => {
            return j;
        });
    });

    if (res) {
        isLoggedIn.update(() => true);
        isAdmin.update(() => res.admin);
    }
    return res;
}

export function setAuthHeader() {
    let auth = localStorage.getItem("auth");
    return "Basic " + auth;
}

export function storeAuth(username,password){
    let base64Auth = btoa(username + ":" + password);
    localStorage.setItem("auth", base64Auth)
}