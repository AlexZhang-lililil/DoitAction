package com.example.orquoll.swen90014_2018_or_quoll.db.initializeData;

import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

public class StrengthData {
    private DAOFactory newDAOFactory;

    public StrengthData (){
        this.newDAOFactory = new DAOFactory();
    }

    public void saveStrength(String tittle,String content,int drawableId){
        Strength strength = new Strength(tittle,content,drawableId);
        if(!strength.isSaved())
        strength.save();
    }

    public void saveAll(){
        saveStrength("Inspiration","Are there things you've seen or heard, or experienced, that you thought were so perfect, so amazing, so beautiful that they really got under your skin and made you feel strong emotions? Inspiration sounds like something you might feel in art class, but it could be anything: the night sky, someone's face, the sun on autumn leaves, a perfect sports performance, a painting. Doesn't matter what it is as long as it really reached inside you, grabbed your heart and stayed with you. If that happens to you a lot, this is one of your strengths.\n", R.drawable.appreciation);
        saveStrength("Connectedness","It's a big universe out there, and it's complex, but that's okay with you. You have a sense of how you fit in with everything around you. You feel like you have a place in something bigger than yourself. Maybe you don't know what it all means, but it gives you comfort to know it all means something. That gives you something to hold on to when life gets confusing, so you never feel really lost.",R.drawable.connectedness);
        saveStrength("Courage","What makes you braver: bungee jumping or standing up for a kid who's being picked on? Sure, it takes nerve to step off a bridge with a rubber band round your ankle, but real courage is doing what you think is right, even when it's hard. It's doing the hard thing even when you're afraid. That's you. Your bravery makes you able to face your fears and do things you think are important.",R.drawable.courage);
        saveStrength("Creativity","Creative people love thinking of new ways to do things.  And it doesn't just mean in art: creative people have revolutionised science, sport, music Ã¢â‚¬â€\u009D everywhere, really. It doesn't matter if you love mixing up tracks or concrete, your imagination is always working. Did you ever have one of those moments where a new idea comes into your head, or a new move on the football field or the dance floor, or a rhyme or a picture or a formula or a recipe or some other exciting thing, and the amazing thing is that you imagined it out of nowhere? That's your creativity, an imaginative talent can be an enormous power in your life.",R.drawable.creativity);
        saveStrength("Curiosity","Why is the sky blue? What's over the next hill? What's over <em>that</em> one? Curious people want to know more. You find the whole world interesting, you go looking for new things to discover and you welcome new experiences. You're the kind of person who gets excited by words like <em>discover</em> and <em>explore</em> and especially <em>mystery</em>.\n",R.drawable.curiosity);
    }

}
