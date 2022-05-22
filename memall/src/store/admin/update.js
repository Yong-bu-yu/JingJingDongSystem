import message from "element-plus/es/el-message";
import {Update} from "../../utils/admin/update";
import store from "../index";

const update = {
    actions: {
        // eslint-disable-next-line no-unused-vars
        async AdminUpdate({commit},param){
            // console.log(param)
            await Update(param)
                // eslint-disable-next-line no-unused-vars
                .then(r => {
                    store.dispatch('Info')
                    message({
                        type: 'success',
                        message: '数据更新成功'
                    })
                    // eslint-disable-next-line no-unused-vars
                }).catch(r =>{
                    console.log(r)
                    message({
                        type: "error",
                        message: "更新错误"
                    })
                })
        }
    }
}

export default update
