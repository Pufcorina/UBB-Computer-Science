--where		5 : ||||| -f
--group		3 : ||| -f
--distinct	2 : || -f
--having	2 : || - f
--join 2+	7 : |||||| -f
--join many	2 : || - f

-- 1. Ce domenii si in ce limba au fost imprumutate carti de ce grupe de clienti
select distinct D.DomainName, Lg.LanguageName, CG.ClientGroupName
from Books B
inner join Domains D on D.DId = B.DId
inner join Languages Lg on Lg.LId = B.LId
inner join Loans L on B.BId = L.BId
inner join Clients C on C.CId = L.CId
inner join ClientGroups CG on CG.CGId = C.CGId

-- 2. Sa se afiseze Titlul, Autorul si Volumul cartilor ce au fost imprumutate si sunt scrise toate in anul 1956 si clientul care a imprumutat Nume si Prenume
select B.Title, B.Author, B.Volume, C.FirstName, C.SecondName from Books B
inner join Loans L on L.BId = B.BId
inner join Clients C on C.CId = L.CId
where B.AppearanceYear = 1956

-- 3. Cate carti au fost imprumutate de fiecare grupa
select CG.ClientGroupName, COUNT(*) as carti_imprumutate from ClientGroups CG
inner join Clients C on C.CGId = CG.CGId
inner join Loans L on C.CId = L.CId
group by CG.ClientGroupName
having COUNT(*) >= 2


-- 4. Sa se afle numele si prenumele persoanelor care au imprumutat cele mai multe carti cat si ce carti au imprumutat
select C.FirstName, C.SecondName from Clients C
inner join Loans L on L.CId = C.CId
inner join Books B on B.BId = L.BId
group by C.FirstName, C.SecondName
having count(*) = 
	(select max(P.maxim) as maximum from
		( 
		select COUNT(*) as maxim from Clients C
		inner join Loans L on L.CId = C.CId
		inner join Books B on B.BId = L.BId
		group by  C.FirstName
		) P
	) 


-- 5. Sa se afiseze grupul cat si numele persoanelor care nu au imprumutat nicio carte
select C.FirstName, C.SecondName, GC.ClientGroupName from Clients C
left join Loans L on L.CId = C.CId
inner join ClientGroups GC on GC.CGId = C.CGId
where L.BId is NULL


-- 6. Ce client are carte de la editura 'Corint Junior'
select C.FirstName, C.SecondName from Clients C
inner join Loans L on C.CId = L.CId
inner join Books B on L.BId = B.BId
inner join Publishing P on P.PId = B.BId
where P.PublishingName = 'Corint Junior'

-- 7. Sa se afiseze numele si prenumele cititorilor de carti publicate dupa anul 2000
select C.FirstName, C.SecondName, B.Title, B.AppearanceYear from Clients C
inner join Loans L on L.CId = C.CId
inner join Books B on B.BId = L.BId
where B.AppearanceYear > 2000

-- 8.Care este editura ce a scos cele mai multe carti in anul 1956
select P.PublishingName from Books B
inner join Publishing P on P.PId = B.PId
where B.AppearanceYear = 1956
group by P.PublishingName
having count(*) = 
	(
	select max(Publicatii.maxim) as maximul from (
		select P.PublishingName, count(*) as maxim from Books B
		inner join Publishing P on P.PId = B.PId
		where B.AppearanceYear = 1956
		group by P.PublishingName
		) Publicatii
	)

-- 9. Sa se afiseze toate combinatiile de domenii si limbi
select distinct D.DomainName, Lg.LanguageName from Domains D
inner join Books B on B.DId = D.DId
inner join Languages Lg on Lg.LId = B.LId


-- 10. toate toate informatiile despre cartile care nu au fost predate pana in data 2002-02-02 sau nu au fost imprumutate deloc
select * from Books B
inner join Loans L on L.BId = B.BId
where L.DueDate > '2002-02-02'
union
select * from Books B
left join Loans L on L.BId = B.BId
where L.BId is null 