import server from "../server";

export function userLogin(param) {
    return server.post('/user/login',param)
}