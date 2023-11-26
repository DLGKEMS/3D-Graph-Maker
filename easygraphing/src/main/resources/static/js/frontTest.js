function frontTest(){
    const btnImage = document.getElementById('btn-create-image');

    btnImage.addEventListener('click',()=>{
        createImage();
    })
}

function createImage(){
    const strMime = 'image/jpeg';
    let img;
    try {
        html2canvas(document.querySelector("#canvas-text-box")).then(canvas => {
            let test=canvas.toDataURL('image/jpeg')
            let link2 = document.createElement('a')
            document.body.appendChild(link2);

            link2.href = test;
            link2.download = 'text.jpg';
            link2.click();
        });
        let imgData = document.getElementById('canvas-box').children[0].toDataURL(strMime);
        img = imgData.replace(strMime, "image/octet-stream");
        const link = document.createElement('a');
        document.body.appendChild(link);
        link.download = 'image.jpg';
        link.href = imgData;
        link.click();
        document.body.removeChild(link);
    } catch (e) {
        console.log(e);
    }
}

frontTest();