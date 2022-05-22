import server from "../server";

export function Upload(param) {
    return server.post('/admin/upload',param)
}
