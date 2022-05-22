import server from "../server";

export function DeleteList(param) {
    return server.post('/admin/deleteList',param)
}
