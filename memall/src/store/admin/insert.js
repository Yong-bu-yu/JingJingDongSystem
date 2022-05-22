import {Insert} from "../../utils/admin/insert";

import message from "element-plus/es/el-message/index"

const insert={
    state: {},
    mutations: {},
    actions: {
        // eslint-disable-next-line no-unused-vars
        async AdminInsert({commit},param){
            // console.log(param)
            await Insert(param)
                // eslint-disable-next-line no-unused-vars
                .then(r => {
                message({
                    type: 'success',
                    message: '数据添加成功！'
                })
                    // eslint-disable-next-line no-unused-vars
            }).catch(r =>{
                    // console.log(r)
                message({
                    type: "error",
                    message: "添加错误，也许是用户名重复了"
                })
            })
        }
    }
}

export default insert
