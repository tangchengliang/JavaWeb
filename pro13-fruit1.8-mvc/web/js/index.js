function delFruit(fid) {
    if(confirm('是否确认删除？')){
        window.location.href='fruit.do?fid='+fid+'&operate=del';
    }
}

function page(page) {
    // 不带operate，默认访问index
    window.location.href="fruit.do?page="+page;
}