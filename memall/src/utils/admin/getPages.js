import server from "../server";

export function Pages(params){
    return server.get('/admin/getPages',{
        params: params
    })
}
