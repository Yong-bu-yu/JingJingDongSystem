import server from "./server";

export function userInfo() {
    return server.get('/user/info')
}