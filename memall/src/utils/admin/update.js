import server from "../server";

export function Update(param) {
    return server.post('/admin/update',param)
}
