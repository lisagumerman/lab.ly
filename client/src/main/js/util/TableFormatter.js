define(['lodash'], function(_) {
    return {
        apply:function(data) {
            var headers = data.header,
                elements = _.foldl(headers, function(tdata, header){
                    tdata.push(data.columns[header].e);
                    return tdata;
            }, []);
            return elements;
        }
    }

});