/*썸네일과 큰 이미지에 사용될 포토 객체*/
class Photo {
    constructor(container, src, x, y, width, height) {
        this.container = container;
        this.img = document.createElement("img");
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.img.src = this.src;

        //style
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";

        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        this.container.appendChild(this.img);
    }
}