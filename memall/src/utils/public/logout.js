import server from "../server";

export function userLogout(){
    return server.post('/user/logout')
}
