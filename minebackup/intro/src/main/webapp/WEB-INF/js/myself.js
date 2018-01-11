jQuery(document).ready(function ($) {
    $.ajax({
        url: "http://localhost:8080/test/index.htm",
        type: 'get', //GET
        crossDomain: true,
        async: true,    //或false,是否异步
        contentType: "application/json",
        timeout: 30000,    //超时时间
        success: function (data) {
            var hrefval
            xinming = $("#xinming");
            birth = $("#birth");
            address = $("#address");
            email = $("#email");
            phone = $("#phone");
            selfnet = $("#selfnet");
            post = $("#post-1");
            postshow = $("#post-1-page");
            resume = $("#resume");
            skills = resume.find(".skills");
            exp = resume.find(".attributes");
            contact = $("#contact-user");

            xinming.html(data.name)
            birth.html(data.birthday)
            address.html(data.address)
            email.html(data.email)
            phone.html(data.phone)
            selfnet.html(data.selfnet)
            hrefval = "http://" + data.selfnet
            selfnet.attr("href", hrefval)
            showblog(data.jarray, post, postshow)
            // $.getScript("js/main.js").done();
            showSkill(data.skill,skills);
            showExperience(data.jwork, exp);
            showContact(data,contact)
            initJs();
        },
        error: function (xhr, textStatus) {
            console.log('错误')
            console.log(xhr)
            console.log(textStatus)
        }
        ,
        complete: function () {
            console.log('结束')

        }
    })
})

var showblog = function (blogs, post, postshow) {
    var blogss = eval(blogs)

    pidpre = "post-"
    pidtpre = "#post-"
    postpre = "post-"
    postsufix = "-page";
    for (var b in blogss) {

        postclone = post.clone();
        postsclone = postshow.clone();

        var title = blogss[b].title
        var content = blogss[b].content
        if (content.length > 10) {
            desc = content.substring(0, 40);
        }

        postclone.find(".text_content").html(title);

        pid = pidpre + b
        postid = postpre + b + postsufix;
        pidtid = pidtpre + b;
        postclone.attr("id", pid);


        postclone.find(".blog-content").find(".fa").append(desc)
        //修改read more中的href
        postclone.find(".row").find(".read_m").attr("href", pidtid);

        postsclone.find(".row ").find(".caps").html(content);
        postsclone.attr("id", postid);

        // blogh.after(bclone)

        post.after(postclone);
        postshow.after(postsclone)
        if (b == 9) {
            break;
        }
    }

    post.remove();
    postshow.remove();


};

var initJs=function () {
    // More blog
    $('a.read_m').click(function() {
        var pagina = $(this).attr('href');
        var postdetail = pagina + '-page';

        if (pagina.indexOf("#post-") != -1) {

            $('#blog-page').hide();

            $(postdetail).show();
            $(".tabs-blog").trigger('click');
        }

        return false;
    });
    // More blog
    $('a.read_more').click(function() {
        var pagina = $(this).attr('href');
        var postdetail = pagina + '-page';

        if (pagina.indexOf("#post-") != -1) {

            $('#blog-page').hide();

            $(postdetail).show();
            $(".tabs-blog").trigger('click');
        }

        return false;

    });
    //pagination All
    $('.content-post a').click(function() {
        var pagina = $(this).attr('href');

        if (pagina == "#blog") {

            $('.content-post').hide();
            $('#blog-page').show();
            $(".tabs-blog").trigger('click');

        }

        return false;

    });

    //pagination blog
    $('.content-post #pagination').click(function() {


        var pagina = $(this).attr('href');
        var postdetail = pagina + '-page';

        if (pagina.indexOf("#post-") != -1) {

            $('#blog-page').hide();
            $('.content-post').hide();

            $(postdetail).show();
            $(".tabs-blog").trigger('click');
        }

        return false;

    });

}
var showSkill = function (data,skills) {
    skillbar = skills.find(".skillbar");

    for(var d in data) {
        barclone = skillbar.clone();
        titile=barclone.find(".skillbar-title");
        percent=barclone.find(".skill-bar-percent");
        titile.html(d);
        percent.html(data[d]+"%");
        barclone.attr("data-percent", data[d]+"%");
        skillbar.after(barclone);
    }
    skillbar.remove();
};
var showExperience=function (data,exp) {
    data=eval(data)
    for(var d in data) {
        expclone = exp.clone();
        title = expclone.find("h5");
        duty = expclone.find(".fa-briefcase");
        time = expclone.find(".fa-calendar");
        desc = expclone.find(".first").find("p");
        if(d=="compare"){
            break;
        }

        title.append(data[d].title)
        duty.append(data[d].duty);
        time.append(data[d].time);
        desc.html(data[d].desc)
        exp.after(expclone);
    }
    exp.remove();
}
showContact = function (data,contact) {
    address = contact.find(".glyphicon-map-marker");
    mail = contact.find(".glyphicon-envelope");
    phone = contact.find(".glyphicon-phone");
    selfnet = contact.find(".glyphicon-globe");
    address.after(data.address);
    mail.after(data.email);
    phone.after(data.phone);
    selfnet.after(data.selfnet);
};
