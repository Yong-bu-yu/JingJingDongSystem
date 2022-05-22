import server from "../server";

export function Delete(param) {
    return server.post('/admin/deleteById',param)
}
