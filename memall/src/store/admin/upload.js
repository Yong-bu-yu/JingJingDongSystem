import message from "element-plus/es/el-message";
import {Upload} from "../../utils/admin/upload";

const update = {
    actions: {
        // eslint-disable-next-line no-unused-vars
        async AdminUpload({commit},param){
            await Upload(param)
                // eslint-disable-next-line no-unused-vars
                .then(r => {
                    message({
                        type: 'success',
                        message: '数据插入成功，请自行更新数据'
                    })
                    // eslint-disable-next-line no-unused-vars
                }).catch(r =>{
                    console.log(r)
                    message({
                        type: "error",
                        message: "插入错误"
                    })
                })
        }
    }
}

export default update
